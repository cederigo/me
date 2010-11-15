X = load('trainset.txt');%xj
C = load('trainclasses.txt');
%CT = [5 4 2 6 6 6 2 3 4 1 3 3 3 4 1 4 1 5 5 5];
CT = load('testclasses.txt')';
T = load('testset.txt'); %x
outfile = fopen('export.txt','w'); 


% x - xj for all x from T
DIF = kron(T,ones(length(X),1)) - repmat(X,length(T),1);

% Di(x) for every x(from T) with every xj(X) 
% only diagonal elemnts are interesting
D = diag(DIF * DIF');

%reshape array so that di(x1) is in first col, di(x2) in second..
D = reshape(D,length(X),length(T));

maximum = max(D(:));
I = [];
q = 3;
for i = [1:q]
    % find minimum and replace it with maximum q-times
    [minV,minI] = min(D);
    I = [ I ; minI ];    
    D(sub2ind(size(D), minI,1:length(minI))) = maximum;       
end
if(q == 1)
   RES = C(I)';
else   
   R=C(I);
   %counts number of unique occurences per col
   n = hist(R,unique(R));
   %find max of occurences -> index of max is the RESult   
   [OCC,RES] = max(n);   
   OCC'
   %conflict resolution -> take min. dist   
   IDX = find(OCC < 2, length(OCC));
   %RES(IDX) = C(I(1,IDX));
   %reject
   RES(IDX) = -1;
end

%reject rate
E = length(find(RES == -1, length(RES)))/length(CT)
%error rate
D = length(find( not(RES == CT) & not(RES == -1), length(RES)))/length(CT)
%recognition rate
C = 1-D-E


fprintf('q=%d\n',q);
fprintf('C=%f\n',C);
fprintf('%d\n',RES);
fprintf('\n');

